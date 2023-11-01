function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var detailsApi = Vue.resource('/details{/id}');

Vue.component('details-form', {
    props: ['details', 'detailAttr'],
    data: function() {
        return {
            detailTitle: '',
            id: ''
        }
    },
    watch: {
        detailAttr: function(newVal, oldVal) {
            this.detailTitle = newVal.detailTitle;
            this.id = newVal.id;
        }
    },
    template: '<div>' +
                '<input type="text" placeholder="Enter detail title" v-model="detailTitle" />' +
                '<input type="button" value="Save" @click="save" />' +
              '</div>',
    methods: {
        save: function() {
            var detail = { detailTitle: this.detailTitle };

            if (this.id) {
                detailsApi.update({id: this.id}, detail).then(result =>
                        result.json().then(data => {
                            var index = getIndex(this.details, data.id);
                            this.details.splice(index, 1, data);
                            this.detailTitle = '';
                            this.id = '';
                        })
                    )
            } else {
                detailsApi.save({}, detail).then(result =>
                    result.json().then(data => {
                        this.details.push(data);
                        this.detailTitle = '';
                    })
                )
            }

        }
    }
});

Vue.component('details-row', {
    props: ['detail', 'editMethod', 'details'],
    template: '<div>' +
        '<i>({{ detail.id }})</i> {{ detail.text }}' +
        '<span style="position: absolute; right: 0">' +
            '<input type="button" value="Edit" @click="edit" />' +
            '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods:  {
        edit: function(detail) {
            this.editMethod(this.detail);
        },
        del: function() {
            detailsApi.remove({id: this.detail.id}).then(result => {
                if (result.ok) {
                    this.details.splice(this.details.indexOf(this.detail), 1)
                }
            })
        }
    }
});

Vue.component('details-list', {
    props: ['details'],
    data: function() {
        return {
            detail: null
        }
    },
    template:
        '<div style="position: relative; width: 300px">' +
            '<details-form :details="details" :detailAttr="detail" />' +
            '<details-row v-for="detail in details" :key="detail.id" :detail="detail" ' +
            ':editMethod="editMethod" :details="details" />' +
        '</div>',
    created: function(){
        detailsApi.get().then(result =>
            result.json().then(data =>
                data.forEach(details => this.details.push(details))
            )
        )
    },
    methods:  {
        editMethod: function(detail) {
            this.detail = detail;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<details-list :details="details" />',
    data: {
        details: []
    }
});
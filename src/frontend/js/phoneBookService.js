import axios from "axios";

function executeGet(url, data) {
    return axios.get(url, {
        params: data
    }).then(response => response.data);
}

function executePost(url, data) {
    return axios.post(url, data).then(response => response.data);
}

function executeDelete(url, data) {
    return axios.delete(url,{
        params: {
            ids: data.join(',')
        }
    }).then(response => response.data);
}

function executePut(url, data) {
    return axios.put(url, data).then(response => response.data);
}

export default class PhoneBookService {
    constructor() {
        this.baseUrl = "/api/contacts";
    }

    getContacts(term) {
        return executeGet(this.baseUrl, {term});
    }

    createContact(contact) {
        return executePost(this.baseUrl, contact);
    }

    deleteContact(contactIds) {
        return executeDelete(this.baseUrl, contactIds);
    }

    saveContact(contact) {
        return executePut(this.baseUrl, contact)
    }
};
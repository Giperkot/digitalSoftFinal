let searchResult;

function templateLoadCallback () {
    cmpCore.registryComponent({
        name: "CSearchResult",
        templateId: "CSearchResultTemplate",
        methods: {

        }
    });

    searchResult = cmpCore.addElement({
        name: "searchResult",
        type: "CSearchResult",
        container: ".search_result",
        properties: {
            defaultPage: "specializationListMain"
        },
        methods: {}
    });
}

document.addEventListener("DOMContentLoaded", function () {
    helper.getHttpPromise({
        method: "GET",
        url: "/externalTemplates/templates.html",
        contentType: "application/json",
    }).then(function (responseObj) {
        let template = document.querySelector("#CSearchResultTemplate");

        template.innerHTML = responseObj.response;
        templateLoadCallback();
    });


    let searchBtn = document.querySelector(".search_btn");
    let searchField = document.querySelector(".search_field");
    let prompt = document.querySelector(".prompt");

    searchBtn.addEventListener("click", function (evt) {

        if (!searchField || !searchField.value) {
            return;
        }

        evt.preventDefault();
        evt.stopPropagation();

        helper.getHttpPromise({
            method: "POST",
            url: "/doFullTextSearch",
            contentType: "application/json",
            jsonData: {
                message: searchField.value
            }
        }).then(function (responseObj) {
            let ans = JSON.parse(responseObj.response);

            searchResult.model = {
                resultList: ans
            };

            searchResult.draw(searchResult.containerElm);
            searchResult.bind();
        });
    });

    prompt.addEventListener("click", function (evt) {
        searchField.value = "орг";
    });

});

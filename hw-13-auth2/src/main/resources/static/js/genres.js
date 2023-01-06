function changeGenres(selectObject, valueId, valueName) {
    var idName = selectObject+".id";
    var idValue = selectObject+".genreName";
    document.getElementById(idName).value = valueId;
    document.getElementById(idValue).value = valueName;
}

function addGenreFields() {
    var parentForSelect = document.getElementById("genres");
    var childs = parentForSelect.children;

    var isCreateHiddenFields = false;
    var isCreateSelect = false;
    for (let child of childs) {
        if (child.tagName == "DIV" && child.getAttribute("id") == "hidden_genres") {
            lastP = child.lastElementChild;
            let copyP = lastP.cloneNode(true);
            copyP.setAttribute("name", "hidden_genre" + child.childElementCount)
            for (let input of copyP.children) {
                if (input.getAttribute("id") == "genres"+(child.childElementCount -1)+".id") {
                    input.setAttribute("id", "genres"+child.childElementCount+".id");
                    input.setAttribute("name", "genres["+child.childElementCount+"].id");
                }
                if (input.getAttribute("id") == "genres"+(child.childElementCount-1)+".genreName") {
                    input.setAttribute("id", "genres"+child.childElementCount+".genreName")
                    input.setAttribute("name", "genres["+child.childElementCount+"].genreName")
                }
            }
            child.append(copyP);
            isCreateHiddenFields = true;
        }

        if (child.tagName == "DIV" && child.getAttribute("id") == "select_genres") {

            lastSelect = child.lastElementChild;
            let copySelect = lastSelect.cloneNode(true);

            copySelect.setAttribute("name", "genres" + child.childElementCount);
            child.append(copySelect);
            isCreateSelect = true;
        }

        if (isCreateSelect && isCreateHiddenFields) {
            break;
        }
    }
}
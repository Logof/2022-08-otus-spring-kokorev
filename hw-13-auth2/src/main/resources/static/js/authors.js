function changeAuthors(selectObject, valueId, valueName) {
    var idName = selectObject+".id";
    var idValue = selectObject+".fullName";
    document.getElementById(idName).value = valueId;
    document.getElementById(idValue).value = valueName;
}

function addAuthorFields() {
    var parentForSelect = document.getElementById("authors");
    var childs = parentForSelect.children;

    var isCreateHiddenFields = false;
    var isCreateSelect = false;
    for (let child of childs) {
        if (child.tagName == "DIV" && child.getAttribute("id") == "hidden_authors") {
            lastP = child.lastElementChild;
            let copyP = lastP.cloneNode(true);
            copyP.setAttribute("name", "hidden_author" + child.childElementCount)
            for (let input of copyP.children) {
                if (input.getAttribute("id") == "authors"+(child.childElementCount -1)+".id") {
                    input.setAttribute("id", "authors"+child.childElementCount+".id");
                    input.setAttribute("name", "authors["+child.childElementCount+"].id");
                }
                if (input.getAttribute("id") == "authors"+(child.childElementCount-1)+".fullName") {
                    input.setAttribute("id", "authors"+child.childElementCount+".fullName")
                    input.setAttribute("name", "authors["+child.childElementCount+"].fullName")
                }
            }

            child.append(copyP);
            isCreateHiddenFields = true;
        }

        if (child.tagName == "DIV" && child.getAttribute("id") == "select_authors") {

            lastSelect = child.lastElementChild;
            let copySelect = lastSelect.cloneNode(true);

            copySelect.setAttribute("name", "authors" + child.childElementCount);
            child.append(copySelect);
            isCreateSelect = true;
        }

        if (isCreateSelect && isCreateHiddenFields) {
            break;
        }
    }
}
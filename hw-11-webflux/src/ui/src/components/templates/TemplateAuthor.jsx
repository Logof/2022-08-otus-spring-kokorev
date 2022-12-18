import React from "react";
import {useNavigate} from "react-router-dom";
import httpCommon from "../../http-common";

export function AuthorAdd() {
       
    const navigate = useNavigate();

    const author = {}

    function handleSubmit(event) {
        console.log("- form submitted - ")
        event.preventDefault();

        let newAuthor = Object.create(author);
        newAuthor.fullName = event.target.fullName.value;

        httpCommon.post("/api/author", newAuthor)
            .then(_ =>  {
                navigate("/")
            })
            .catch(error => {
                console.error(`[ERROR] While updating product!`);
                console.error(error);
            })

    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div className="col-auto d-none d-lg-block ">
                    <div className="row g-0 align-items-center">
                        <label htmlFor="fullName" className="col-form-label">ФИО автора: </label>
                        <input type="text" id="fullName" className="form-control" name="fullName" />
                    </div>
                    <div className="col-auto p-4 d-flex flex-column position-static">
                        <button type="submit" className="btn btn-outline-success btn-sm">Сохранить</button>
                    </div>
                </div>
            </div>
        </form>
    )
}
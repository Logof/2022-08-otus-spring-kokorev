import React, { useState }  from "react";
import {Link, redirect, useNavigate} from "react-router-dom";
import Axios from "axios";

export function GenreAdd() {
    
    const navigate = useNavigate();

    const genre = {}

    function handleSubmit(event) {
        console.log(event.target.genreName.value);
        
        event.preventDefault();
        let newGenre = Object.create(genre);
        newGenre.genreName = event.target.genreName.value;

        Axios.post("http://localhost:8010/proxy/api/genre", newGenre)
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
                        <label htmlFor="genreName" className="col-form-label">Жанр: </label>
                        <input type="text" id="genreName" className="form-control" name="genreName" />
                    </div>
                    <div className="col-auto p-4 d-flex flex-column position-static">
                        <button type="submit" className="btn btn-outline-success btn-sm">Сохранить</button>
                    </div>
                </div>
            </div>
        </form>
    )
}
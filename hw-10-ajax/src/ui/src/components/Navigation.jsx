import React from "react";
import {useDispatch, useSelector} from "react-redux";

function Navigation() {
    const dispatch = useDispatch();
    
    return (
        <header className="blog-header lh-1 py-3">
            <div className="row flex-nowrap justify-content-between align-items-center">
                <div className="col-4 pt-1">
                    <a className="link-secondary" href="https://otus.ru">Otus</a>
                </div>
                <div className="col-4 text-center">
                    <a className="blog-header-logo text-dark" href="/">Библиотека</a>
                </div>
                <div className="col-4 d-flex justify-content-end align-items-center">
                    <a className="link-secondary" href="https://github.com/Logof/2022-08-otus-spring-kokorev">GitHub</a>
                </div>
            </div>
        </header>
    );
}

export default Navigation;
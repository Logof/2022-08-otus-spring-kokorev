import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import { Helmet } from 'react-helmet';
import {
    Home,
    Navigation,
    BookPage,
    AuthorPage,
    GenrePage
} from "./components";
import React from "react";

export default function App(){
    return (
        <div className="container">
            <Router>
                <Navigation/>
                
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/book/:id" element={<BookPage/>}/>
                    <Route path="/author" element= {<AuthorPage />} />
                    <Route path="/genre" element= {<GenrePage />} />
                </Routes>
            </Router>
        </div>
    )
}
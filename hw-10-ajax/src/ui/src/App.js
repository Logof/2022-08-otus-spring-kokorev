import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {
    Home,
    Navigation,
    BookPage
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
            </Routes>

            {/*<Footer/>*/}
        </Router>
        </div>
    )
}
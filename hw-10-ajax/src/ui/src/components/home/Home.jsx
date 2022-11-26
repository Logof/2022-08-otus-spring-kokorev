import React, {useEffect} from "react";
import Supply from "./Supply";


function Home() {
    useEffect(() => {
        document.title = "Bookshop"
    }, []);

    return (
        <div className="main-wrap">
            <div className="grid-container">
                <Supply />
            </div>
        </div>
    );
}

export default Home;

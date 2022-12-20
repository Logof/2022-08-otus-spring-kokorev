import React, {useEffect, useState} from "react";
import httpCommon from "../../http-common";
import { BookForList } from "../templates/Template";
import { Row, Col } from 'react-bootstrap';

export default function Supply() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        httpCommon.get('/api/book')
            .then(response => {
                setProducts(response.data);
            })
            .catch(response => {
                console.log(response)
            });
    }, [])

    return (
        <Row xs={2}>
            {products.map(book => (
                <Col className='col-md-6' key={book.isbn}>
                    <BookForList product={book} key={book.isbn}/>
                </Col>))}
        </Row>
        
    )
}
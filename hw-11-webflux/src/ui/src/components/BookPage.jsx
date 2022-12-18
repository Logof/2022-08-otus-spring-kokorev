import React, {useEffect, useState} from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams, useNavigate } from "react-router-dom";
import httpCommon from "../http-common";

import { BookEdit, BookDetails, Comments } from "./templates/Template";
import { fetchBook, removeSelectedBook, createdBook } from "../redux/actions/bookActions";

import { fetchComments } from "../redux/actions/commentActions";
import { fetchAuthors } from "../redux/actions/authorActions";
import { fetchGenres } from "../redux/actions/genreActions";

export default function BookPage() {
    const book = useSelector(state => state.book);
    const comment = useSelector(state => state.comment);
    const genre = useSelector(state => state.genre);
    const author = useSelector(state => state.author);

    const [isEdit, setIsEdit] = useState(false)

    const dispatch = useDispatch();

    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (id==="new") {
            setIsEdit(true);
            return () => {dispatch(createdBook)}
        } else {
            dispatch(fetchBook(id));
            return () => {
            dispatch(removeSelectedBook())
            };
        }
    }, [dispatch, id]);

    useEffect(() => { dispatch(fetchComments(id)); }, [dispatch, id]);

    useEffect(() => { dispatch(fetchGenres(id)); }, [dispatch, id]);

    useEffect(() => { dispatch(fetchAuthors(id)); }, [dispatch, id]);


    function homePage() {
        navigate("/")
    }

    function toggleEditMode() {
        if (isEdit) {
            setIsEdit(false);
        } else {
            setIsEdit(true);
        }
    }

    function deleteBook() {
        httpCommon.delete(`/api/book/${book.isbn}`)
            .then(_ => {
                homePage()
            })
            .catch(error => {
                console.log("Error occured!");
            });
    }

    if (isEdit) {
        return (
            <div id="book-page">
                <h3>Редактирование</h3>
                <div className="d-flex justify-content-between align-items-center">
                    <div className="btn-group">
                        <button className="btn btn-sm btn-outline-secondary" onClick={homePage}>К списку</button>
                        { id!=="new" ? <button className="btn btn-sm btn-outline-secondary" onClick={deleteBook}>Удалить</button> :  <></>}
                    </div>
                </div>
                { 
                    id==="new" ? 
                        <BookEdit product={book} genreListAll={genre} authorListAll={author} />
                    :
                        Object.keys(book).length !== 0 ? <BookEdit product={book} genreListAll={genre} authorListAll={author} /> : '404 Product Not Found!'
                    
                }
            </div>
        );
    }

    return (
            <div id="book-page">
                <h3>Описание</h3>
                <div className="d-flex justify-content-between align-items-center">
                    <div className="btn-group">
                        <button className="btn btn-sm btn-outline-secondary" onClick={homePage}>К списку</button>
                        <button className="btn btn-sm btn-outline-secondary" onClick={toggleEditMode}>Редактировать</button>
                        <button className="btn btn-sm btn-outline-secondary" onClick={deleteBook}>Удалить</button>
                    </div>
                </div>
                { Object.keys(book).length !== 0 ? <BookDetails product={book}/> : '404 Product Not Found!' }
                { Object.keys(comment).length !== 0 ? <Comments comments={comment} /> : ''}
            </div>
    );

};
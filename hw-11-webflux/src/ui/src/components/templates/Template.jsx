import React, { useState }  from "react";
import {Link, useNavigate} from "react-router-dom";
import Axios from "axios";

export function BookForList({product}) {
    return (
        <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col-auto d-none d-lg-block">
                <svg className="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg"
                         role="img" aria-label="Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                </svg>
            </div>
            <div className="col p-4 d-flex flex-column position-static">
                <div className="row">
                    <h3 className="mb-0">{product.title}</h3>
                    <div className="mb-1 text-muted">ISBN: {product.isbn}</div>
                </div>
                <div className="row">
                    <ul className="product-card-text mb-auto">
                        <AuthorList authors={product.authors}/> 
                    </ul>
                </div>
                <div className="row">
                    <div className="d-flex justify-content-between align-items-center">
                        <Link className="btn btn-sm btn-outline-secondary" to={`/book/${product.isbn}`}>Подробнее</Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export function BookDetails({product}) {
    return (
        <div className="container-fluid">
            <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div className="col-md-2" >
                    <svg className="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg"
                        role="img" aria-label="Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                        <rect width="100%" height="100%" fill="#55595c"></rect>
                    </svg>
                </div>
                <div className="col-md-10">
                    <h3 className="mb-0">{product.title}</h3>
                    <div className="mb-1 text-muted">ISBN: {product.isbn}</div>
                    <div className='d-flex justify-content-between align-items-center'>
                        <GenreList genres={product.genres} />
                    </div>
                    <div className="d-flex justify-content-between align-items-center">
                        <AuthorList authors={product.authors}/> 
                    </div>
                </div>
            </div>
        </div>
    )
}

export function GenreList({genres}) {
    if (genres.length === 0 ) return "----";
    return (
      <ul className="btn-toggle-nav list-unstyled fw-normal pb-1 small">
        {genres.map((genre) => (
          <li key={genre.id}>{genre.genreName}</li>
        ))}
      </ul>
    );
}

export function AuthorList({authors}) {
    if (authors.length === 0 ) return "----";
    return (
      <ul className="btn-toggle-nav list-unstyled fw-normal pb-1 small">
        {authors.map((author) => (
          <li key={author.id}>{author.fullName}</li>
        ))}
      </ul>
    );
}

export function BookEdit({product, genreListAll, authorListAll}) {
    const [genreList, setGenres] = useState(product.genres || []);
    const [authorList, setAuthors] = useState(product.authors || []);

    const navigate = useNavigate();

    function homePage() {
        navigate("/")
    }

    function deleteBook() {
        Axios.delete(`http://localhost:8010/proxy/api/book/${product.isbn}`)
            .then(_ => {
                homePage()
            })
            .catch(error => {
                console.log("Error occured!");
            });
    }

    function handleSubmit(event) {
        console.log("- form submitted - ")
        event.preventDefault();
        product.isbn = event.target.isbn.value;
        product.title = event.target.title.value;
        product.genres = genreList; //event.target.genres;
        product.authors = authorList; //event.target.authors;
        console.log(product);
        Axios.post("http://localhost:8010/proxy/api/book", product)
            .then(_ =>  {
                navigate("/")
            })
            .catch(error => {
                console.error(`[ERROR] While updating product!`);
                console.error(error);
            })

    }

    function updateGenreList(newValue) {
        console.log("new value = ", newValue)
        const newGenreList = genreList.map(obj => {
            if (obj.id === newValue.id) {
                return newValue;
            }
            return obj;
        });
        setGenres(newGenreList);
    };

    function updateAuthorList(newValue) {
        console.log(newValue)

        const newAuthorList = authorList.map(obj => {
            if (obj.id === newValue.id) {
                return newValue;
            }
            return obj;
        });
        setAuthors(newAuthorList);
    };

    return (
        <div className="container-fluid">
            <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div className="col-md-2" >
                    <svg className="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg"
                        role="img" aria-label="Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                        <rect width="100%" height="100%" fill="#55595c"></rect>
                    </svg>
                </div>
                <div className="col-md-10">
                    <form onSubmit={handleSubmit} className="form-inline">
                        <div className="form-group g-0 align-items-center">
                            <label htmlFor="inputISBN" className="col-form-label">ISBN: </label>
                            <input type="text" id="inputISBN" className="form-control" name="isbn" defaultValue={product.isbn} />
                        </div>
                        <div className="form-group g-0 align-items-center">
                            <label htmlFor="inputTitle" className="col-form-label">Заголовок: </label>
                            <input type="text" id="inputTitle" className="form-control" name="title" defaultValue={product.title} />
                        </div>
                        <div className="form-group g-0 align-items-center">
                            <label htmlFor="selectGenres" className="col-form-label">Жанр(ы): </label>
                            {
                                genreList.length > 0 
                                    ? genreList.map((genre, index) => {
                                        return (
                                            <>
                                                <select key={genre.id} 
                                                        className="form-select" 
                                                        onChange={e => { updateGenreList(genreListAll.find(genre => {return genre.id === e.target.value}))}}>
                                                    {genreListAll.map(genreItem => {
                                                                return (
                                                                    (genre.id === genreItem.id) 
                                                                    ? 
                                                                    <option key={genreItem.id} value={genreItem.id} selected='selected'>
                                                                        {genreItem.genreName}
                                                                    </option>
                                                                    :
                                                                    <option key={genreItem.id} value={genreItem.id}>
                                                                        {genreItem.genreName}
                                                                    </option>
                                                                ) })
                                                    }
                                                </select>
                                                <div className="col-auto">
                                                    <button type="button" className="btn btn-outline-success btn-sm" 
                                                            onClick={() => setGenres(genreList.filter((_, id) => id !== index))} > Удалить </button>
                                                    <button type="button" className="btn btn-outline-success btn-sm" onClick={() => setGenres([...genreList, genreListAll[0]])} > Добавить </button>
                                                </div>
                                            </>)
                                        })
                                    : genreListAll.length > 0 
                                        ? 
                                        <button type="button" 
                                            className="btn btn-outline-success btn-sm" 
                                            onClick={() => setGenres([...genreList, genreListAll[0]])} > Добавить </button>
                                        :
                                        <a href="/genre" type="button" className="btn btn-outline-success btn-sm">
                                            Заполнить список жанров
                                        </a>
                            }
                        </div>    
                        <div className="form-group g-0 align-items-center">
                            <label className="col-form-label">Автор(ы): </label>
                            {
                                authorList.length > 0 ? authorList.map((author, index) => { return (                                
                            <><select key={index} className="form-select" onChange={e => {
                                                        updateAuthorList(authorListAll.find(author => author.id === e.target.value))}} >
                                                        {authorListAll.map(authorItem => {
                                                            return (
                                                                (author.id === authorItem.id) 
                                                                ? 
                                                                <option key={authorItem.id} value={authorItem.id} selected='selected'>
                                                                    {authorItem.fullName}
                                                                </option>
                                                                :
                                                                <option key={authorItem.id} value={authorItem.id}>
                                                                    {authorItem.fullName}
                                                                </option>
                                                            )
                                                        })}
                            </select>
                            <div className="col-auto">
                                <button type="button" className="btn btn-outline-success btn-sm" 
                                                        onClick={() => setAuthors(authorList.filter((_, id) => id !== index))} > Удалить </button>
                                <button type="button" className="btn btn-outline-success btn-sm" onClick={() => setAuthors([...authorList, authorListAll[0]])} > Добавить </button>
                            </div></>)})
                                    : authorListAll.length > 0 ?
                                        <button type="button" className="btn btn-outline-success btn-sm" onClick={() => setAuthors([...authorList, authorListAll[0]])} > Добавить </button>
                                        :
                                        <button type="button" 
                                                className="btn btn-outline-success btn-sm" onClick={navigate("/author")}>
                                            Заполнить список авторов
                                        </button>
                                }
                        </div>    
                        <div className="form-group-auto p-4 flex-column justify-content-between">
                            <div className="btn-group">
                                <button className="btn btn-sm btn-outline-secondary" type="submit">Сохранить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    )
}

export function Comments({comments}) {
    return (
        <div>
            {comments.map((comment, index) => (
                <div key={comment.id} className="media-block g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div className="media-body">
                        <div className="mar-btm">
                            <p className="text-muted text-sm">#<i className="fa fa-mobile fa-lg">{comment.id}</i></p>
                        </div>
                        <p>{comment.commentText}</p>
                    </div>
                </div>
            ))}
        </div>
    );
}
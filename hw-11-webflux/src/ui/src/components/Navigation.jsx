import React from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

function Navigation() {
    
    return (
        <>
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
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand href="/">Библиотека</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/">Список книг</Nav.Link>
                        <NavDropdown title="Добавить" id="basic-nav-dropdown">
                            <NavDropdown.Item href="/book/new">книгу</NavDropdown.Item>
                            <NavDropdown.Item href="/genre">жанр</NavDropdown.Item>
                            <NavDropdown.Item href="/author">автора</NavDropdown.Item>
                            
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
            </Navbar>
        
        </>
    );
}

export default Navigation;


/*
        <div className="col-md-12">
            <div className="col p-4 d-flex flex-column position-static">
                <div className="d-flex justify-content-between align-items-center">
                    <div className="btn-group">
                        <a className="btn btn-sm btn-outline-secondary" href="/">Добавить книгу</a>
                    </div>
                </div>
            </div>
        </div>
*/
import {createStore, applyMiddleware, compose} from "redux";
import reducers from "./reducers/index";
import thunk from "redux-thunk";
import {loadState, saveState} from "../lib/states"

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const preloadedStates = {
    book: loadState('book'),
    author: loadState('author'),
    genre: loadState('genre'),
    comment: loadState('comment'),
}

const store = createStore(
    reducers,
    preloadedStates,
    composeEnhancers(applyMiddleware(thunk))
);

store.subscribe(() => {
    saveState('book', store.getState().book)
    saveState('author', store.getState().author)
    saveState('genre', store.getState().genre)
})

export default store;

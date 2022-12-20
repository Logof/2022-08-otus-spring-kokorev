import { combineReducers } from "redux";
import { authorReducer } from "./authorReducer";
import { genreReducer } from "./genreReducer";
import { bookReducer } from "./bookReducer";
import { commentReducer } from "./commentReducer";

const reducers = combineReducers({
  author: authorReducer,
  genre: genreReducer,
  book: bookReducer,
  comment: commentReducer
});

export default reducers;

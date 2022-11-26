import { GenreActionTypes } from "../constants/action-types";

export const genreReducer = (state = {}, {type, payload}) => {
    switch (type) {
        case GenreActionTypes.SELECTED_GENRES:
            return payload;
        default:
            return state;
    }
}
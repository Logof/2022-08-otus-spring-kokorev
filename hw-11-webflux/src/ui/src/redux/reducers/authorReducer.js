import {AuthorActionTypes} from "../constants/action-types";

export const authorReducer = (state = {}, {type, payload}) => {
    switch (type) {
        case AuthorActionTypes.SELECTED_AUTHORS:
            return payload;
        default:
            return state;
    }
}
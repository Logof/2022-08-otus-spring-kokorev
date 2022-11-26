import {CommentActionTypes} from "../constants/action-types";

export const commentReducer = (state = {}, {type, payload}) => {
    switch (type) {
        case CommentActionTypes.SELECTED_COMMENTS:
            return payload;
        default:
            return state;
    }
}
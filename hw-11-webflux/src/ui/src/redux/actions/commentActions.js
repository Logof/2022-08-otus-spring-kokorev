import {CommentActionTypes} from "../constants/action-types";
import httpCommon from "../../http-common";

export const fetchComments = (productID) => async (dispatch) => {
    httpCommon.get(`/api/comment/${productID}`)
        .then(response => {
            dispatch({type: CommentActionTypes.SELECTED_COMMENTS, payload: response.data});
        })
        .catch(response => {
            console.log(response)
        });
};

export const selectComment = (product) => {
    return {
        type: CommentActionTypes.SELECTED_COMMENTS,
        payload: product
    }
}

export const removeSelectedComment = () => {
    return {
        type: CommentActionTypes.REMOVE_SELECTED_COMMENT,
    };
};
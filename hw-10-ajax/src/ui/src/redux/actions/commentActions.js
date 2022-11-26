import {CommentActionTypes} from "../constants/action-types";
import Axios from 'axios';

export const fetchComments = (productID) => async (dispatch) => {
    Axios.get(`http://localhost:8010/proxy/api/comment/${productID}`)
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
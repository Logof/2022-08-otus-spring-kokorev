import {ProductActionTypes} from "../constants/action-types";
import httpCommon from "../../http-common";

export const fetchBook = (productID) => async (dispatch) => {
    httpCommon.get(`/api/book/${productID}`)
        .then(response => {
            dispatch({type: ProductActionTypes.SELECTED_PRODUCT, payload: response.data});
        })
        .catch(response => {
            console.log(response)
        });
};

export const selectBook = (book) => {
    return {
        type: ProductActionTypes.SELECTED_PRODUCT,
        payload: book
    }
}

export const removeSelectedBook = () => {
    return {
        type: ProductActionTypes.REMOVE_SELECTED_PRODUCT,
    };
};

export const createdBook = () => {
    return {
        type: ProductActionTypes.CREATE_PRODUCT
    };
}

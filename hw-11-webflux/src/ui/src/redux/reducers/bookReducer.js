import { ProductActionTypes } from "../constants/action-types";

export const bookReducer = (state = {}, {type, payload}) => {
    switch (type) {
        case ProductActionTypes.SELECTED_PRODUCT:
            return payload;
        case ProductActionTypes.REMOVE_SELECTED_PRODUCT:
            return {};
        default:
            return state;
    }
}
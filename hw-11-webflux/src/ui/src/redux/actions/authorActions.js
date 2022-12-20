import {AuthorActionTypes} from "../constants/action-types";
import httpCommon from "../../http-common";

export const fetchAuthors = () => async (dispatch) => {
    httpCommon.get(`/api/author/`)
        .then(response => {
            dispatch({type: AuthorActionTypes.SELECTED_AUTHORS, payload: response.data});
        })
        .catch(response => {
            console.log(response)
        });
};
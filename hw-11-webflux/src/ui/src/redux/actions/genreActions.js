import {GenreActionTypes} from "../constants/action-types";
import httpCommon from "../../http-common";

export const fetchGenres = () => async (dispatch) => {
    httpCommon.get(`/api/genre/`)
        .then(response => {
            dispatch({type: GenreActionTypes.SELECTED_GENRES, payload: response.data});
        })
        .catch(response => {
            console.log(response)
        });
};

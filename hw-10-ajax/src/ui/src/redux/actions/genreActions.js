import {GenreActionTypes} from "../constants/action-types";
import Axios from 'axios';

export const fetchGenres = () => async (dispatch) => {
    Axios.get(`http://localhost:8010/proxy/api/genre/`)
        .then(response => {
            dispatch({type: GenreActionTypes.SELECTED_GENRES, payload: response.data});
        })
        .catch(response => {
            console.log(response)
        });
};

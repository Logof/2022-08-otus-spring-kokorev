import {AuthorActionTypes} from "../constants/action-types";
import Axios from 'axios';

export const fetchAuthors = () => async (dispatch) => {
    Axios.get(`http://localhost:8010/proxy/api/author/`)
        .then(response => {
            dispatch({type: AuthorActionTypes.SELECTED_AUTHORS, payload: response.data});
        })
        .catch(response => {
            console.log(response)
        });
};
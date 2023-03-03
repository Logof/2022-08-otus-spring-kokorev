import axios from 'axios';

export interface CategoryRecord {
  id: number;
  name: string;
  subCategory: CategoryRecord[];
}

export function getAllcategories() {
  return axios.get<CategoryRecord[]>(`categories`);
}

export function getCategoryById(id: number) {
  return axios.get<CategoryRecord>(`categories/${id}`);
}

export function putCategory(req: CategoryRecord){

}

export function deleteCategoryById(id: number) {

}


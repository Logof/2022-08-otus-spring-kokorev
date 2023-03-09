import axios from 'axios';
import { CategoryRecord } from '../category/index';

export interface InfoCardRecord {
  id: number;
  name: string;
  nameAlt: string | '';
  category: CategoryRecord;
  releaseType: string | '';
  images: string | '';
  description: string | '';
}

export function getAllInfoCardInCategory(categoryId: number) {
  return axios.get<InfoCardRecord[]>(`categories/${categoryId}/info`);
}

export function getAllInfoCardInCategoryAndName(
  categoryId: number,
  name: string
) {
  return axios.get<InfoCardRecord[]>(`categories/${categoryId}?like=${name}`);
}

export function getCategoryById(id: number) {
  return axios.get<InfoCardRecord>(`info/${id}`);
}

export function putCategory(req: InfoCardRecord) {}

export function deleteCategoryById(id: number) {}

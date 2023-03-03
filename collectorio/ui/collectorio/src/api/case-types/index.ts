import axios from 'axios';

export interface CaseTypeRecord {
  id: number;
  name: string;
};

export function getCaseTypeListByCategory(categoryId: number) {
  return axios.get<CaseTypeRecord[]>(`categories/${categoryId}/cases/`);
}
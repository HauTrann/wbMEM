import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';

type EntityResponseType = HttpResponse<IMedicalSuppliesType>;
type EntityArrayResponseType = HttpResponse<IMedicalSuppliesType[]>;

@Injectable({ providedIn: 'root' })
export class MedicalSuppliesTypeService {
  public resourceUrl = SERVER_API_URL + 'api/medical-supplies-types';

  constructor(protected http: HttpClient) {}

  create(medicalSuppliesType: IMedicalSuppliesType): Observable<EntityResponseType> {
    return this.http.post<IMedicalSuppliesType>(this.resourceUrl, medicalSuppliesType, { observe: 'response' });
  }

  update(medicalSuppliesType: IMedicalSuppliesType): Observable<EntityResponseType> {
    return this.http.put<IMedicalSuppliesType>(this.resourceUrl, medicalSuppliesType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedicalSuppliesType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedicalSuppliesType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

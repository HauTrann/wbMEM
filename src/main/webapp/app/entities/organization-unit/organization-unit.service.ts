import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrganizationUnit } from 'app/shared/model/organization-unit.model';

type EntityResponseType = HttpResponse<IOrganizationUnit>;
type EntityArrayResponseType = HttpResponse<IOrganizationUnit[]>;

@Injectable({ providedIn: 'root' })
export class OrganizationUnitService {
  public resourceUrl = SERVER_API_URL + 'api/organization-units';

  constructor(protected http: HttpClient) {}

  create(organizationUnit: IOrganizationUnit): Observable<EntityResponseType> {
    return this.http.post<IOrganizationUnit>(this.resourceUrl, organizationUnit, { observe: 'response' });
  }

  update(organizationUnit: IOrganizationUnit): Observable<EntityResponseType> {
    return this.http.put<IOrganizationUnit>(this.resourceUrl, organizationUnit, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrganizationUnit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganizationUnit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

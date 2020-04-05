import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDepartmentEmployee } from 'app/shared/model/department-employee.model';

type EntityResponseType = HttpResponse<IDepartmentEmployee>;
type EntityArrayResponseType = HttpResponse<IDepartmentEmployee[]>;

@Injectable({ providedIn: 'root' })
export class DepartmentEmployeeService {
  public resourceUrl = SERVER_API_URL + 'api/department-employees';

  constructor(protected http: HttpClient) {}

  create(departmentEmployee: IDepartmentEmployee): Observable<EntityResponseType> {
    return this.http.post<IDepartmentEmployee>(this.resourceUrl, departmentEmployee, { observe: 'response' });
  }

  update(departmentEmployee: IDepartmentEmployee): Observable<EntityResponseType> {
    return this.http.put<IDepartmentEmployee>(this.resourceUrl, departmentEmployee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDepartmentEmployee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDepartmentEmployee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

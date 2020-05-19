import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Department, IDepartment } from 'app/shared/model/department.model';
import { DepartmentService } from './department.service';
import { DepartmentComponent } from './department.component';
import { DepartmentDetailComponent } from './department-detail.component';
import { DepartmentUpdateComponent } from './department-update.component';

@Injectable({ providedIn: 'root' })
export class DepartmentResolve implements Resolve<IDepartment> {
  constructor(private service: DepartmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepartment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((department: HttpResponse<Department>) => {
          if (department.body) {
            return of(department.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Department());
  }
}

export const departmentRoute: Routes = [
  {
    path: '',
    component: DepartmentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalDeviceMngApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DepartmentDetailComponent,
    resolve: {
      department: DepartmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DepartmentUpdateComponent,
    resolve: {
      department: DepartmentResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'medicalDeviceMngApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DepartmentUpdateComponent,
    resolve: {
      department: DepartmentResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'medicalDeviceMngApp.department.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

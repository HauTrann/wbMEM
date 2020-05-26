import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrganizationUnit, OrganizationUnit } from 'app/shared/model/organization-unit.model';
import { OrganizationUnitService } from './organization-unit.service';
import { OrganizationUnitComponent } from './organization-unit.component';
import { OrganizationUnitDetailComponent } from './organization-unit-detail.component';
import { OrganizationUnitUpdateComponent } from './organization-unit-update.component';

@Injectable({ providedIn: 'root' })
export class OrganizationUnitResolve implements Resolve<IOrganizationUnit> {
  constructor(private service: OrganizationUnitService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrganizationUnit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((organizationUnit: HttpResponse<OrganizationUnit>) => {
          if (organizationUnit.body) {
            return of(organizationUnit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrganizationUnit());
  }
}

export const organizationUnitRoute: Routes = [
  {
    path: '',
    component: OrganizationUnitComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.ADMIN],
      defaultSort: 'id,asc',
      pageTitle: 'medicalDeviceMngApp.organizationUnit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrganizationUnitDetailComponent,
    resolve: {
      organizationUnit: OrganizationUnitResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'medicalDeviceMngApp.organizationUnit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrganizationUnitUpdateComponent,
    resolve: {
      organizationUnit: OrganizationUnitResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'medicalDeviceMngApp.organizationUnit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrganizationUnitUpdateComponent,
    resolve: {
      organizationUnit: OrganizationUnitResolve
    },
    data: {
      authorities: [Authority.ADMIN],
      pageTitle: 'medicalDeviceMngApp.organizationUnit.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

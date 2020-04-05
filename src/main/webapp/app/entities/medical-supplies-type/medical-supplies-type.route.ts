import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedicalSuppliesType, MedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';
import { MedicalSuppliesTypeService } from './medical-supplies-type.service';
import { MedicalSuppliesTypeComponent } from './medical-supplies-type.component';
import { MedicalSuppliesTypeDetailComponent } from './medical-supplies-type-detail.component';
import { MedicalSuppliesTypeUpdateComponent } from './medical-supplies-type-update.component';

@Injectable({ providedIn: 'root' })
export class MedicalSuppliesTypeResolve implements Resolve<IMedicalSuppliesType> {
  constructor(private service: MedicalSuppliesTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedicalSuppliesType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medicalSuppliesType: HttpResponse<MedicalSuppliesType>) => {
          if (medicalSuppliesType.body) {
            return of(medicalSuppliesType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedicalSuppliesType());
  }
}

export const medicalSuppliesTypeRoute: Routes = [
  {
    path: '',
    component: MedicalSuppliesTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalDeviceMngApp.medicalSuppliesType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MedicalSuppliesTypeDetailComponent,
    resolve: {
      medicalSuppliesType: MedicalSuppliesTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.medicalSuppliesType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MedicalSuppliesTypeUpdateComponent,
    resolve: {
      medicalSuppliesType: MedicalSuppliesTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.medicalSuppliesType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MedicalSuppliesTypeUpdateComponent,
    resolve: {
      medicalSuppliesType: MedicalSuppliesTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.medicalSuppliesType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

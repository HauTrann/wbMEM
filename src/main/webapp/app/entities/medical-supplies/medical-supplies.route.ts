import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMedicalSupplies, MedicalSupplies } from 'app/shared/model/medical-supplies.model';
import { MedicalSuppliesService } from './medical-supplies.service';
import { MedicalSuppliesComponent } from './medical-supplies.component';
import { MedicalSuppliesDetailComponent } from './medical-supplies-detail.component';
import { MedicalSuppliesUpdateComponent } from './medical-supplies-update.component';

@Injectable({ providedIn: 'root' })
export class MedicalSuppliesResolve implements Resolve<IMedicalSupplies> {
  constructor(private service: MedicalSuppliesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedicalSupplies> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((medicalSupplies: HttpResponse<MedicalSupplies>) => {
          if (medicalSupplies.body) {
            return of(medicalSupplies.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MedicalSupplies());
  }
}

export const medicalSuppliesRoute: Routes = [
  {
    path: '',
    component: MedicalSuppliesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalDeviceMngApp.medicalSupplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MedicalSuppliesDetailComponent,
    resolve: {
      medicalSupplies: MedicalSuppliesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.medicalSupplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MedicalSuppliesUpdateComponent,
    resolve: {
      medicalSupplies: MedicalSuppliesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.medicalSupplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MedicalSuppliesUpdateComponent,
    resolve: {
      medicalSupplies: MedicalSuppliesResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalDeviceMngApp.medicalSupplies.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

import { Route } from '@angular/router';

import { AccUserComponent } from './acc_user.component';

export const ACC_USER_ROUTE: Route = {
  path: 'acc_user',
  component: AccUserComponent,
  data: {
    authorities: [],
    pageTitle: 'home.title'
  }
};

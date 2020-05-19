import { Route } from '@angular/router';

import { NavbarBottomComponent } from './navbar-bottom.component';

export const navbarBottomRoute: Route = {
  path: '',
  component: NavbarBottomComponent,
  outlet: 'navbar-bottom'
};

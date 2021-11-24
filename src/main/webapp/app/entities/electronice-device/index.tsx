import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ElectroniceDevice from './electronice-device';
import ElectroniceDeviceDetail from './electronice-device-detail';
import ElectroniceDeviceUpdate from './electronice-device-update';
import ElectroniceDeviceDeleteDialog from './electronice-device-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ElectroniceDeviceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ElectroniceDeviceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ElectroniceDeviceDetail} />
      <ErrorBoundaryRoute path={match.url} component={ElectroniceDevice} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ElectroniceDeviceDeleteDialog} />
  </>
);

export default Routes;

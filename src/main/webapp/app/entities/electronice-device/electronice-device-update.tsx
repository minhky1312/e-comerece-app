import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './electronice-device.reducer';
import { IElectroniceDevice } from 'app/shared/model/electronice-device.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { Loaisanpham } from 'app/shared/model/enumerations/loaisanpham.model';

export const ElectroniceDeviceUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const electroniceDeviceEntity = useAppSelector(state => state.electroniceDevice.entity);
  const loading = useAppSelector(state => state.electroniceDevice.loading);
  const updating = useAppSelector(state => state.electroniceDevice.updating);
  const updateSuccess = useAppSelector(state => state.electroniceDevice.updateSuccess);
  const loaisanphamValues = Object.keys(Loaisanpham);
  const handleClose = () => {
    props.history.push('/electronice-device' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...electroniceDeviceEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          loaiSanpham: 'DIENTHOAI',
          ...electroniceDeviceEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="myappApp.electroniceDevice.home.createOrEditLabel" data-cy="ElectroniceDeviceCreateUpdateHeading">
            <Translate contentKey="myappApp.electroniceDevice.home.createOrEditLabel">Create or edit a ElectroniceDevice</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="electronice-device-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('myappApp.electroniceDevice.tenSanpham')}
                id="electronice-device-tenSanpham"
                name="tenSanpham"
                data-cy="tenSanpham"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.giaBan')}
                id="electronice-device-giaBan"
                name="giaBan"
                data-cy="giaBan"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.loaiSanpham')}
                id="electronice-device-loaiSanpham"
                name="loaiSanpham"
                data-cy="loaiSanpham"
                type="select"
              >
                {loaisanphamValues.map(loaisanpham => (
                  <option value={loaisanpham} key={loaisanpham}>
                    {translate('myappApp.Loaisanpham.' + loaisanpham)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('myappApp.electroniceDevice.soluong')}
                id="electronice-device-soluong"
                name="soluong"
                data-cy="soluong"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.sanphammoi')}
                id="electronice-device-sanphammoi"
                name="sanphammoi"
                data-cy="sanphammoi"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.overView')}
                id="electronice-device-overView"
                name="overView"
                data-cy="overView"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.dungLuong')}
                id="electronice-device-dungLuong"
                name="dungLuong"
                data-cy="dungLuong"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.maHoa')}
                id="electronice-device-maHoa"
                name="maHoa"
                data-cy="maHoa"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.tocDo')}
                id="electronice-device-tocDo"
                name="tocDo"
                data-cy="tocDo"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.mTBF')}
                id="electronice-device-mTBF"
                name="mTBF"
                data-cy="mTBF"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.nANDFlash')}
                id="electronice-device-nANDFlash"
                name="nANDFlash"
                data-cy="nANDFlash"
                type="text"
              />
              <ValidatedField
                label={translate('myappApp.electroniceDevice.heDieuhanhhotro')}
                id="electronice-device-heDieuhanhhotro"
                name="heDieuhanhhotro"
                data-cy="heDieuhanhhotro"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/electronice-device" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ElectroniceDeviceUpdate;

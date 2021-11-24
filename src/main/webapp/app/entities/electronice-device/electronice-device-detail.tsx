import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './electronice-device.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ElectroniceDeviceDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const electroniceDeviceEntity = useAppSelector(state => state.electroniceDevice.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="electroniceDeviceDetailsHeading">
          <Translate contentKey="myappApp.electroniceDevice.detail.title">ElectroniceDevice</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.id}</dd>
          <dt>
            <span id="tenSanpham">
              <Translate contentKey="myappApp.electroniceDevice.tenSanpham">Ten Sanpham</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.tenSanpham}</dd>
          <dt>
            <span id="giaBan">
              <Translate contentKey="myappApp.electroniceDevice.giaBan">Gia Ban</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.giaBan}</dd>
          <dt>
            <span id="loaiSanpham">
              <Translate contentKey="myappApp.electroniceDevice.loaiSanpham">Loai Sanpham</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.loaiSanpham}</dd>
          <dt>
            <span id="soluong">
              <Translate contentKey="myappApp.electroniceDevice.soluong">Soluong</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.soluong}</dd>
          <dt>
            <span id="sanphammoi">
              <Translate contentKey="myappApp.electroniceDevice.sanphammoi">Sanphammoi</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.sanphammoi ? 'true' : 'false'}</dd>
          <dt>
            <span id="overView">
              <Translate contentKey="myappApp.electroniceDevice.overView">Over View</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.overView}</dd>
          <dt>
            <span id="dungLuong">
              <Translate contentKey="myappApp.electroniceDevice.dungLuong">Dung Luong</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.dungLuong}</dd>
          <dt>
            <span id="maHoa">
              <Translate contentKey="myappApp.electroniceDevice.maHoa">Ma Hoa</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.maHoa}</dd>
          <dt>
            <span id="tocDo">
              <Translate contentKey="myappApp.electroniceDevice.tocDo">Toc Do</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.tocDo}</dd>
          <dt>
            <span id="mTBF">
              <Translate contentKey="myappApp.electroniceDevice.mTBF">M TBF</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.mTBF}</dd>
          <dt>
            <span id="nANDFlash">
              <Translate contentKey="myappApp.electroniceDevice.nANDFlash">N AND Flash</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.nANDFlash}</dd>
          <dt>
            <span id="heDieuhanhhotro">
              <Translate contentKey="myappApp.electroniceDevice.heDieuhanhhotro">He Dieuhanhhotro</Translate>
            </span>
          </dt>
          <dd>{electroniceDeviceEntity.heDieuhanhhotro}</dd>
        </dl>
        <Button tag={Link} to="/electronice-device" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/electronice-device/${electroniceDeviceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ElectroniceDeviceDetail;

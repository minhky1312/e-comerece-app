import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './electronice-device.reducer';
import { IElectroniceDevice } from 'app/shared/model/electronice-device.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ElectroniceDevice = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const electroniceDeviceList = useAppSelector(state => state.electroniceDevice.entities);
  const loading = useAppSelector(state => state.electroniceDevice.loading);
  const totalItems = useAppSelector(state => state.electroniceDevice.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { match } = props;

  return (
    <div>
      <h2 id="electronice-device-heading" data-cy="ElectroniceDeviceHeading">
        <Translate contentKey="myappApp.electroniceDevice.home.title">Electronice Devices</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="myappApp.electroniceDevice.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="myappApp.electroniceDevice.home.createLabel">Create new Electronice Device</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {electroniceDeviceList && electroniceDeviceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="myappApp.electroniceDevice.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tenSanpham')}>
                  <Translate contentKey="myappApp.electroniceDevice.tenSanpham">Ten Sanpham</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('giaBan')}>
                  <Translate contentKey="myappApp.electroniceDevice.giaBan">Gia Ban</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('loaiSanpham')}>
                  <Translate contentKey="myappApp.electroniceDevice.loaiSanpham">Loai Sanpham</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('soluong')}>
                  <Translate contentKey="myappApp.electroniceDevice.soluong">Soluong</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('sanphammoi')}>
                  <Translate contentKey="myappApp.electroniceDevice.sanphammoi">Sanphammoi</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('overView')}>
                  <Translate contentKey="myappApp.electroniceDevice.overView">Over View</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dungLuong')}>
                  <Translate contentKey="myappApp.electroniceDevice.dungLuong">Dung Luong</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('maHoa')}>
                  <Translate contentKey="myappApp.electroniceDevice.maHoa">Ma Hoa</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tocDo')}>
                  <Translate contentKey="myappApp.electroniceDevice.tocDo">Toc Do</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('mTBF')}>
                  <Translate contentKey="myappApp.electroniceDevice.mTBF">M TBF</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nANDFlash')}>
                  <Translate contentKey="myappApp.electroniceDevice.nANDFlash">N AND Flash</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('heDieuhanhhotro')}>
                  <Translate contentKey="myappApp.electroniceDevice.heDieuhanhhotro">He Dieuhanhhotro</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {electroniceDeviceList.map((electroniceDevice, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${electroniceDevice.id}`} color="link" size="sm">
                      {electroniceDevice.id}
                    </Button>
                  </td>
                  <td>{electroniceDevice.tenSanpham}</td>
                  <td>{electroniceDevice.giaBan}</td>
                  <td>
                    <Translate contentKey={`myappApp.Loaisanpham.${electroniceDevice.loaiSanpham}`} />
                  </td>
                  <td>{electroniceDevice.soluong}</td>
                  <td>{electroniceDevice.sanphammoi ? 'true' : 'false'}</td>
                  <td>{electroniceDevice.overView}</td>
                  <td>{electroniceDevice.dungLuong}</td>
                  <td>{electroniceDevice.maHoa}</td>
                  <td>{electroniceDevice.tocDo}</td>
                  <td>{electroniceDevice.mTBF}</td>
                  <td>{electroniceDevice.nANDFlash}</td>
                  <td>{electroniceDevice.heDieuhanhhotro}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${electroniceDevice.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${electroniceDevice.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${electroniceDevice.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="myappApp.electroniceDevice.home.notFound">No Electronice Devices found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={electroniceDeviceList && electroniceDeviceList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default ElectroniceDevice;

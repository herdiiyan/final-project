import { takeLatest, put } from "redux-saga/effects";
import {
  FIND_CUSTOMER,
  FIND_CUSTOMER_ERROR,
  FIND_CUSTOMER_SUCCES,
} from "../actions/CustomersActions";
import { filterFetch } from "../utils/apiUtils";
import { url } from "../utils/api";


function* findOneCustomers(action) {
  try {
    const data = yield filterFetch(url + '/customer/' + action.cif, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'},
    });
    yield put({
      type: FIND_CUSTOMER_SUCCES,
      data: data
    });
  } catch (error) {
    yield put({
      type: FIND_CUSTOMER_ERROR,
      error: error
    });
  }
}

export function* watchFindOneCustomers() {
  yield takeLatest(FIND_CUSTOMER, findOneCustomers);
}

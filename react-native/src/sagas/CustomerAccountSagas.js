import { takeLatest, put } from "redux-saga/effects";
import {
  FIND_ACCOUNT,
  FIND_ACCOUNT_ERROR,
  FIND_ACCOUNT_SUCCES,
  FIND_ACCOUNT_NUMBER,
  FIND_ACCOUNT_NUMBER_SUCCES,
  FIND_ACCOUNT_NUMBER_ERROR,
  PUT_ACCOUNT_BALANCE,
  PUT_ACCOUNT_BALANCE_SUCCES,
  PUT_ACCOUNT_BALANCE_ERROR
} from "../actions/CustomerAccount";
import { filterFetch } from "../utils/apiUtils";
import { url } from "../utils/api";

function* getAccountByCIF(action) {
  try {
    const data = yield filterFetch(url + '/account/cif?cif=' + action.cif, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'},
    });
    yield put({
      type: FIND_ACCOUNT_SUCCES,
      data: data
    });
  } catch (error) {
    yield put({
      type: FIND_ACCOUNT_ERROR,
      error: error
    });
  }
}

function* getAccountByAccountNumber(action) {
  try {
    const data = yield filterFetch(url + '/account/' + action.accountNumber, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json'},
    });
    yield put({
      type: FIND_ACCOUNT_NUMBER_SUCCES,
      data: data
    });
  } catch (error) {
    yield put({
      type: FIND_ACCOUNT_NUMBER_ERROR,
      error: error
    });
  }
}

function* putBalance(action) {
  try {
    const data = yield filterFetch(url + '/account/topup/' + action.accountNumber, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json'},
      body: JSON.stringify(action.data)
    });
    yield put({
      type: PUT_ACCOUNT_BALANCE_SUCCES,
      data: data
    });
  } catch (error) {
    yield put({
      type: PUT_ACCOUNT_BALANCE_ERROR,
      error: error
    });
  }
}

export function* watchgetAccountByCIF() {
  yield takeLatest(FIND_ACCOUNT, getAccountByCIF);
}

export function* watchgetAccountByAccountNumber() {
  yield takeLatest(FIND_ACCOUNT_NUMBER, getAccountByAccountNumber);
}

export function* watchputBalance() {
  yield takeLatest(PUT_ACCOUNT_BALANCE, putBalance);
}

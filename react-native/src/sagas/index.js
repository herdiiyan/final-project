import { watchFindOneCustomers } from "./CustomerSaga";
import { watchgetAccountByCIF, watchgetAccountByAccountNumber, watchputBalance } from "./CustomerAccountSagas";
import { watchFindLoan } from "./LoanSagas";
import { watchFindBilling } from "./BillingSagas";
import { all, fork } from "redux-saga/effects";

export default function* sagas() {
  yield all([
    fork(watchFindOneCustomers),
    fork(watchgetAccountByCIF),
    fork(watchFindLoan),
    fork(watchFindBilling),
    fork(watchgetAccountByAccountNumber),
    fork(watchputBalance)
  ]);
}

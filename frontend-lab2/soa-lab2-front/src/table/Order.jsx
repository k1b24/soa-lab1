import {useState} from "react";

const Order = ({setUpperOrder, updateContent}) => {

    return <details className="dropdown">
        <summary className="m-1 btn" onClick={setUpperOrder}>Order</summary>
        </details>
}

export default Order
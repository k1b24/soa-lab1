import {useState} from "react";

const Order = ({setUpperOrder, updateContent}) => {

    return <details className="dropdown">
        <summary className="m-1 btn" onClick={setUpperOrder}>Order</summary>
{/*          <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52"> */}
{/* //             <button className={"btn"} onClick={setUpperOrder}>Order</button> */}
{/* //        </ul> */}
        </details>
}

export default Order
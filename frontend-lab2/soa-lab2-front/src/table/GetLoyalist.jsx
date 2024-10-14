import {fetchGetLoyalist} from "../utils/space-marines/api";

const GetLoyalist = ({alertWithMessage}) => {


    const onButtonClick = () => {
        fetchGetLoyalist(alertWithMessage)
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Get Loyalist</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <button className={"btn btn-outline btn-success"} onClick={onButtonClick}>Get Loyalist</button>
                </div>
            </li>
        </ul>
    </details>
}

export default GetLoyalist
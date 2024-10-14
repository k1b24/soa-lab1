import {useState} from "react";
import {fetchLoadOnStarship} from "../utils/starships/api";

const LoadOnStarship = ({alertWithMessage}) => {

    const [starshipId, setStarshipId] = useState("")
    const [spaceMarineId, setSpaceMarineId] = useState("")

    const onCreateButtonClick = () => {
        fetchLoadOnStarship(
            starshipId,
            spaceMarineId,
            alertWithMessage
        )
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Load marine on starship</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <div>
                        <label htmlFor="starshipIdArea">starshipId</label>
                        <textarea id={"starshipIdArea"} value={starshipId} onChange={e => setStarshipId(e.target.value)} placeholder={"starshipId"}/>
                        <label htmlFor="spaceMarineIdArea">spaceMarineId</label>
                        <textarea id={"spaceMarineIdArea"} value={spaceMarineId} onChange={e => setSpaceMarineId(e.target.value)} placeholder={"spaceMarineId"}/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onCreateButtonClick}>Load</button>
                </div>
            </li>
        </ul>
    </details>
}

export default LoadOnStarship
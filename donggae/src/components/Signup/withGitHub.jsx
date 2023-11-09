import styles from "./withGitHub.module.css"
import { useNavigate } from "react-router-dom";
import github from '../../image/GitHub.png';

export default function withGitHub() {
    const history = useNavigate();
        return(
            <div><img src={github}></img></div>
        );
};
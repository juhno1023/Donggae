import styles from "./Mainpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebar";

export default function Home() {
    const history = useNavigate();
    return (
        <div className={styles.default}>
            <Header />
            <div className={styles.inner}>
                <Sidebar/>
                <div className={styles.body}>
                    default
                </div>
            </div>
        </div>

    );
}
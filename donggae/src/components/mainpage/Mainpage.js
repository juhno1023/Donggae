import styles from "./Mainpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "./Header";
import Sidebar from "./Sidebar";

export default function Home() {
    const history = useNavigate();
    return (
        <div className={styles.default}>
            <Header />
            <div className={styles.inner}>
                <Sidebar/>
            </div>
        </div>

    );
}
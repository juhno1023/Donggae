import { useEffect, useRef, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Sidebars.css';
import bgImg from '../../image/donggae.png';
import styles from "./Sidebar.module.css"
import profile from '../../image/profile.png';

const sidebarNavItems = [
    {
        display: 'Main',
        icon: <i className='bx bx-home'></i>,
        to: '/main',
        section: 'main'
    },
    {
        display: 'Leader',
        icon: <i className='bx bx-star'></i>,
        to: '/leader',
        section: 'leader'
    },
    {
        display: 'MyTeam',
        icon: <i className='bx bx-calendar'></i>,
        to: '/userteam',
        section: 'userteam'
    },
    {
        display: 'Ranking',
        icon: <i className='bx bx-user'></i>,
        to: '/rank',
        section: 'rank'
    },
]

const Sidebar = () => {
    const sidebarRef = useRef();
    const indicatorRef = useRef();


    return <div className='sidebar'>
        <div className="sidebar__logo">
        <img className="logoimg" src={bgImg} alt="Donggae Logo" />
        </div>
        <div ref={sidebarRef} className="sidebar__menu">
            <nav className={styles.navigation}>
                <ul>
                <li className={`sidebar__menu__item`}><Link to='/main'>메인</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/posting'>posting</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/leader'>leader</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/userteam'>myteam</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/rank'>rank</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/teaminfo'>내가 속한 팀</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/mypage'>마이페이지</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/rank'>로그아웃</Link></li>
                </ul>
            </nav>
            {/* <div className={styles.profilebox}>
                <div className={styles.logo}>
                    <img className={styles.logoimg} src={profile} alt="Profile" />
                    <div>
                        <div className={styles.title}>Dabin</div>
                    </div>
                </div>
            </div> */}
           
        </div>
    </div>;
};

export default Sidebar;